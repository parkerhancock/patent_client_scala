package ptab

import ujson.Value

import scala.collection.immutable.HashMap

class PtabTrial (data: Value) {
  /*
  Example Data:
  {
      "trialNumber": "IPR2016-00831",
      "applicationNumber": "09026118",
      "patentNumber": "6162705",
      "petitionerPartyName": "Commissariat a l’Energie Atomique et aux Energies Alternatives",
      "patentOwnerName": "Silicon Genesis Corporation",
      "inventorName": "FRANCOIS HENLEY",
      "prosecutionStatus": "Terminated-Settled",
      "filingDate": "2016-04-01",
      "accordedFilingDate": "2016-04-01",
      "institutionDecisionDate": "2016-09-28",
      "lastModifiedDatetime": "2017-07-06T16:06:59",
  }

   */
  val record = data("results")(0)
  val trialNumber = record("trialNumber").str
  val applicationNumber = record("applicationNumber").str
  val patentNumber = record("patentNumber").str
  val petitionerPartyName = record("petitionerPartyName").str
  val patentOwnerName = record("patentOwnerName").str
  val inventorName = record("inventorName").str
  val prosecutionStatus = record("prosecutionStatus").str
  val filingDate = PtabModel.date_format.parse(record("filingDate").str)
  val accordedFilingDate = PtabModel.date_format.parse(record("accordedFilingDate").str)
  val lastModifiedDatetime = PtabModel.datetime_format.parse(record("lastModifiedDatetime").str)

  def documents() = {
    new PtabDocumentManager(HashMap("trialNumber" -> trialNumber))
  }
}

object PtabTrial {
  def objects = {
    new PtabTrialManager()
  }

}
