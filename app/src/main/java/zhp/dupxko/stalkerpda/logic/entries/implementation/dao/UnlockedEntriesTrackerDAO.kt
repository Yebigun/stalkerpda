package zhp.dupxko.stalkerpda.logic.entries.implementation.dao

import zhp.dupxko.stalkerpda.logic.entries.model.EntryIdentifier

interface UnlockedEntriesTrackerDAO {
    fun addUnlockedEntry(identifier: EntryIdentifier)
    fun getUnlockedEntries(): List<EntryIdentifier>
}