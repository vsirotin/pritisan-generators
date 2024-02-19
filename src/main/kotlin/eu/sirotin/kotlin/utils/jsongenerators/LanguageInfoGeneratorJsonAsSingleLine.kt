package eu.sirotin.kotlin.utils.jsongenerators

class LanguageInfoGeneratorJsonAsSingleLine : LanguageInfoGenerator(){
    override fun processContextSourceLine(line: String) {
        currentText = line.replace("{", "{$ls")
        currentText = currentText.replace('"' + ",", '"' + "," + ls)
        currentText = currentText.replace("}", "$ls}")
        contextProcessingCompleted = true
    }
}