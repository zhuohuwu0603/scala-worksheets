import java.io.{File, PrintWriter}

import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types.DoubleType

import scala.collection.Map
import scala.io.Source


case class Book(author: String)
case class Review(id: Int)


val spark = SparkSession.builder()
  .master("local[3]")
  .appName("test")
  .config("spark.driver.allowMultipleContexts", "true")
  .getOrCreate()

import spark.implicits._

org.apache.spark.sql.catalyst.encoders.OuterScopes.addOuterScope(this)

val bookFrame = List(
  Book("book1"),
  Book("book2"),
  Book("book3"),
  Book("book4"),
  Book("book5")
).toDS()


val reviewFrame = List(
  Review(1),
  Review(2),
  Review(3),
  Review(4)
).toDS()

bookFrame
  .write
  .format("com.databricks.spark.xml")
  .option("rootTag", "books")
  .option("rowTag", "book")
  .mode(SaveMode.Overwrite)
  .save("/tmp/books/")

reviewFrame
  .write
  .format("com.databricks.spark.xml")
  .option("rootTag", "reviews")
  .option("rowTag", "review")
  .mode(SaveMode.Overwrite)
  .save("/tmp/review")


def concatFiles(path:String):List[String] = {
  val fileList = new File(path)
    .listFiles
    .filter(
      _.getName.startsWith("part")
    )
    .map(_.getAbsolutePath)
    .zipWithIndex
    .toList

    val lastNum = fileList.length-1

  fileList.flatMap{
    case (filePath, index) =>
      val lines = Source.fromFile(filePath).getLines().toList
      if(index == 0) lines.init
      else if(index == lastNum) lines.tail
      else lines.init.tail
  }.map("    " + _)
}

val lines = List("<xml>","<library>") ++ concatFiles("/tmp/books/") ++ concatFiles("/tmp/review/") ++ List("</xml>")
new PrintWriter("/tmp/target.xml"){
  write(lines.mkString("\n"))
  close
}
