package ptab

import requests._
import ujson._

class PtabTrialManager {

  def get (input: String) ={
    val response = requests.get("https://ptabdata.uspto.gov/ptab-api/trials?trialNumber=IPR2016-00831")
    val data = ujson.read(response.text)
    new PtabTrial(data)
  }
}

object PtabTrialManager {
  def objects () = {
    new PtabTrialManager
  }
}
