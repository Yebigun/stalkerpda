package zhp.dupxko.stalkerpda.logic.entrytracking.implementation.dao

import zhp.dupxko.stalkerpda.logic.entrytracking.exception.UnlockedEntriesTrackerFileNotExistsException
import zhp.dupxko.stalkerpda.logic.entrytracking.model.EntryIdentifier
import java.io.File

internal class FileBasedUnlockedEntriesTrackerDAO(
    fileName: String, filesDir: File
)
    : UnlockedEntriesTrackerDAO {

    private val file: File

    init {
        file = File(filesDir,fileName)
        if (!file.exists()) {
            file.createNewFile()
        }
    }

    override fun addUnlockedEntry(identifier: EntryIdentifier) {
        if(file.exists()) {
            val currentFileContent = file.readText()
            val updatedFileContent = currentFileContent + identifier.name + ","
            file.writeText(updatedFileContent)
        } else {
            throw UnlockedEntriesTrackerFileNotExistsException()
        }
    }
    override fun getUnlockedEntries(): List<EntryIdentifier> {
        if(file.exists()) {
            val currentFileContent = file.readText()
            return currentFileContent.split(",").map { str -> EntryIdentifier.valueOf(str) }
        } else {
            throw UnlockedEntriesTrackerFileNotExistsException()
        }
    }
}