package eu.sirotin.kotlin.utils.jsongenerators
import java.io.File


fun generateLanguageMaps() {

    LanguageInfoGeneratorJsonAsSingleLine().processFile("src/main/resources/raw-language-maps/settings.txt",
        "results/assets/languages/features/components/settings/")

    LanguageInfoGeneratorJsonAsMultiLine().processFile("src/main/resources/raw-language-maps/main.txt",
        "results/assets/languages/core/components/main/")
}


abstract class LanguageInfoGenerator() {
    private var source: String = ""
    private var targetDir: String = ""
    private var fullFilePath: String = ""
    protected var currentText = ""
    protected var contextProcessingCompleted: Boolean = false

    protected val ls: String = System.lineSeparator()

    fun processFile(source: String, targetDir: String) {
        this.source = source
        this.targetDir = targetDir
        fullFilePath = ""
        currentText = "";
        contextProcessingCompleted = false
        val lines: List<String> = File(source).readLines()
        lines.forEach { line -> processLine(line) }
    }

    protected open fun processLine(line: String) {
        if (line.isEmpty()) return;

        if (fullFilePath.isEmpty()) {
            generateTargetFileName(line)
            println("currentFileName=" + fullFilePath)
            return
        }

        processContextSourceLine(line)

        if(contextProcessingCompleted)writeCurrentTextInFile()
    }

    protected open fun generateTargetFileName(line: String) {
        val s = line.replace(":", "");
        fullFilePath = targetDir + s.trim() + ".json"
    }

    abstract fun processContextSourceLine(line: String)

    private fun writeCurrentTextInFile() {
        val file = File(fullFilePath)
        file.parentFile.mkdirs()
        file.delete()
        file.writeText(currentText)
        fullFilePath = ""
        currentText = ""
        contextProcessingCompleted = false
    }
}
