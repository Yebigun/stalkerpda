package zhp.dupxko.stalkerpda.logic.entries.implementation

import arrow.core.Either
import zhp.dupxko.stalkerpda.logic.entries.exception.EntryAlreadyUnlockedException
import zhp.dupxko.stalkerpda.logic.entries.implementation.dao.UnlockedEntriesTrackerDAO
import zhp.dupxko.stalkerpda.logic.entries.model.EntryIdentifier

class UnlockedEntriesService(
    private val trackerDao: UnlockedEntriesTrackerDAO
) {
    fun unlockEntry(identifier: EntryIdentifier): Either<EntryAlreadyUnlockedException, Unit> {
        return if (isEntryUnlocked(identifier)) {
            Either.Left(EntryAlreadyUnlockedException(identifier))
        } else {
            trackerDao.addUnlockedEntry(identifier)
            Either.Right(Unit)
        }
    }

    //todo do zastanowienia czy w ogóle będzie case żeby tego użyć, bo pisanie testów do tego jest męczące
//    fun unlockMultipleEntries(identifiers: List<EntryIdentifier>): Either<Set<EntryAlreadyUnlockedException>, Unit> {
//        val duplicates = mutableSetOf<EntryAlreadyUnlockedException>()
//        identifiers.forEach {
//            if(!isEntryUnlocked(it)) {
//                trackerDao.addUnlockedEntry(it)
//            } else {
//                duplicates.add(EntryAlreadyUnlockedException(it))
//            }
//        }
//        return if(duplicates.isEmpty())
//            Either.Right(Unit)
//            else Either.Left(duplicates)
//    }

    fun isEntryUnlocked(identifier: EntryIdentifier): Boolean {
        return trackerDao.getUnlockedEntries().contains(identifier)
    }

    fun getAllUnlockedEntries(): List<EntryIdentifier> {
        return trackerDao.getUnlockedEntries()
    }
}