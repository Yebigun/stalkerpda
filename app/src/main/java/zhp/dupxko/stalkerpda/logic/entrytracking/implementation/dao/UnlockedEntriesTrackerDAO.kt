package zhp.dupxko.stalkerpda.logic.entrytracking.implementation.dao

import zhp.dupxko.stalkerpda.logic.entrytracking.model.EntryIdentifier

interface UnlockedEntriesTrackerDAO {
    fun addUnlockedEntry(identifier: EntryIdentifier)
    fun getUnlockedEntries(): List<EntryIdentifier>
}