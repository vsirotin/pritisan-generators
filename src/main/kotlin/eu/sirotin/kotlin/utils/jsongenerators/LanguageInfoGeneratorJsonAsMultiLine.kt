package eu.sirotin.kotlin.utils.jsongenerators

class LanguageInfoGeneratorJsonAsMultiLine : LanguageInfoGenerator(){
    override fun processContextSourceLine(line: String) {
        currentText += line + ls
        if(line.contains('}'))contextProcessingCompleted = true
    }
}