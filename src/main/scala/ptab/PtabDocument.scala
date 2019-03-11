package ptab

import ujson.Value

import scala.collection.immutable.HashMap

class PtabDocument (data: Value){
  /* Sample Data
   {
      "trialNumber": "IPR2016-00831",
      "sizeInBytes": 3226184,
      "filingParty": "petitioner",
      "filingDatetime": "2016-04-01T15:59:39",
      "lastModifiedDatetime": "2016-04-06T13:50:24",
      "documentNumber": "1009",
      "title": "U.S. Provisional Application No. 60/046,276",
      "mediaType": "application/pdf",
      "id": 230910,
      "type": "exhibit",
    }
   */
  val trialNumber = data("trialNumber").str
  val sizeInBytes = data("sizeInBytes").num.toInt
  val filingParty = data("filingParty").str
  val filingDatetime = PtabModel.datetime_format.parse(data("filingDatetime").str)
  val lastModifiedDatetime = PtabModel.datetime_format.parse(data("lastModifiedDatetime").str)
  val documentNumber = data("documentNumber").str
  val title = data("title").str
  val mediaType = data("mediaType").str
  val id = data("id").num.toInt
  val kind = data("type").str
}

object PtabDocument {
  def objects() = {
    new PtabDocumentManager(new HashMap())
  }
}
