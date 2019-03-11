package ptab

import ujson.Value

import scala.collection.immutable.HashMap
import scala.collection.mutable

class PtabDocumentManager(val params: HashMap[String, String]) extends Iterator[PtabDocument]{
  val pages = new mutable.HashMap[Int, Value]()
  var iterator_index = 0

  def filter(new_params: HashMap[String, String])= {
    new PtabDocumentManager(params ++ new_params)
  }

  override def length(): Int = {
    getPage(0)("metadata")("count").num.toInt
  }

  def getPage(page_no: Int): Value = {
    if (!pages.contains(page_no)) {
      val request_params = params + ("offset" -> (page_no * 25).toString(), "limit" -> 25.toString())
      val response = requests.get("https://ptabdata.uspto.gov/ptab-api/documents", params = request_params)
      val data = ujson.read(response.text)
      pages put (page_no, data)
    }
    (pages get page_no).get
  }

  def getIndex(index: Int): PtabDocument = {
    val page_no = index / 25
    val position = index % 25
    val data = getPage(page_no)("results")(position)
    new PtabDocument(data)
  }

  def hasNext = iterator_index < length()
  def next = {
    val element = getIndex(iterator_index)
    iterator_index += 1
    element
  }

}
