package PtabSuite

import org.scalatest.FunSuite
import ptab._

class PtabSuite extends FunSuite {
  test("IPR2016-00831 should have data for IPR2016-00831") {
    val trial = PtabTrial.objects.get("IPR2016-00831")
    val date_format = new java.text.SimpleDateFormat("yyyy-MM-dd")
    val datetime_format = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    assert("IPR2016-00831" == trial.trialNumber)
    assert("09026118" == trial.applicationNumber)
    assert("6162705" == trial.patentNumber)
    assert("FRANCOIS HENLEY" == trial.inventorName)
    assert("Terminated-Settled" == trial.prosecutionStatus)
    assert(date_format.parse("2016-04-01") == trial.filingDate)
    assert(datetime_format.parse("2017-07-06T16:06:59") == trial.lastModifiedDatetime)
  }
  test("IPR2016-00831 should have several documents") {
    assert(PtabTrial.objects.get("IPR2016-00831").documents.length == 82)
  }
  test("IPR2016-00831 should have documents with data") {
    val doc: PtabDocument = PtabTrial.objects.get("IPR2016-00831").documents.next()
    assert("IPR2016-00831" == doc.trialNumber)
    assert("petitioner" == doc.filingParty)
    assert(PtabModel.datetime_format.parse("2016-04-01T15:59:39") == doc.filingDatetime)
    assert(3226184 == doc.sizeInBytes)
  }
}
