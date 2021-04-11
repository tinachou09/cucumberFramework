package util

object ScenarioContext {
  //private so not access to other place
  private var variables: Map[String, Any] = Map.empty

  def set(key:String, value:Any) ={
    variables = variables + (key -> value)
  }

}
