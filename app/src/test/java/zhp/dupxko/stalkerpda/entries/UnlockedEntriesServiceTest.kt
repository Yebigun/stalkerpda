package zhp.dupxko.stalkerpda.entries

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import zhp.dupxko.stalkerpda.logic.entries.exception.EntryAlreadyUnlockedException
import zhp.dupxko.stalkerpda.logic.entries.implementation.UnlockedEntriesService
import zhp.dupxko.stalkerpda.logic.entries.implementation.dao.UnlockedEntriesTrackerDAO
import zhp.dupxko.stalkerpda.logic.entries.model.EntryIdentifier

class UnlockedEntriesServiceTest {

    private class MockUnlockedEntriesTrackerDAO(
        val entries: MutableList<EntryIdentifier>
    )
        : UnlockedEntriesTrackerDAO {
        override fun addUnlockedEntry(identifier: EntryIdentifier) {
            entries.add(identifier)
        }

        override fun getUnlockedEntries(): List<EntryIdentifier> {
            return entries.toList()
        }
    }

    companion object {
        @JvmStatic
        fun unlockEntryParams(): List<MutableList<EntryIdentifier>> {
            return listOf(
                mutableListOf(),
                mutableListOf(EntryIdentifier.ENTRY_2),
                mutableListOf(
                    EntryIdentifier.ENTRY_2,
                    EntryIdentifier.ENTRY_3,
                    EntryIdentifier.ENTRY_4,
                    EntryIdentifier.ENTRY_5,
                    EntryIdentifier.ENTRY_6,
                    EntryIdentifier.ENTRY_7,
                    EntryIdentifier.ENTRY_8,
                    EntryIdentifier.ENTRY_9,
                ),
            )
        }
    }

    @ParameterizedTest
    @MethodSource("unlockEntryParams")
    fun givenEntryNotUnlocked_whenUnlockEntry_shouldSaveUnlockedEntry(
        alreadyUnlockedEntries: MutableList<EntryIdentifier>
    ) {
        //given
        val entryToUnlock = EntryIdentifier.ENTRY_1
        val dao = MockUnlockedEntriesTrackerDAO(alreadyUnlockedEntries)
        val service = UnlockedEntriesService(dao)

        //when
        service.unlockEntry(entryToUnlock)

        //then
        Assertions.assertTrue(dao.entries.contains(entryToUnlock), "Entry was not unlocked")
    }

    @Test
    fun givenEntryUnlocked_whenUnlockEntry_shouldReturnCorrectException() {
        //given
        val entryToUnlock = EntryIdentifier.ENTRY_1
        val dao = MockUnlockedEntriesTrackerDAO(mutableListOf(entryToUnlock))
        val service = UnlockedEntriesService(dao)
        val expectedException = EntryAlreadyUnlockedException(entryToUnlock)

        //when
        val result = service.unlockEntry(entryToUnlock)

        //then
        Assertions.assertTrue(
            result.isLeft()
                    && result.leftOrNull() is EntryAlreadyUnlockedException
                    && result.leftOrNull()!!.message!!.equals(expectedException.message),
            "Result was not an expected exception"
        )
    }

    //todo patrz deklaracja metody "unlockMultipleEntries"
//    @ParameterizedTest
//    @MethodSource("unlockEntryParams")
//    fun givenEntriesNotUnlocked_whenUnlockMultipleEntries_shouldSaveUnlockedEntries(
//        alreadyUnlockedEntries: MutableList<EntryIdentifier>
//    ) {
//        //given
//        val entriesToUnlock = listOf(EntryIdentifier.ENTRY_1, EntryIdentifier.ENTRY_3)
//        val dao = MockUnlockedEntriesTrackerDAO(alreadyUnlockedEntries)
//        val service = UnlockedEntriesService(dao)
//
//        //when
//        service.unlockMultipleEntries(entriesToUnlock)
//
//        //then
//        Assertions.assertTrue(dao.entries.containsAll(entriesToUnlock), "Entries were not unlocked")
//    }
//
//    @Test
//    fun givenEntriesAre_whenUnlockMultipleEntries_shouldReturnCorrectExceptions() {
//        //given
//        val entriesToUnlock = listOf(
//            EntryIdentifier.ENTRY_1,
//            EntryIdentifier.ENTRY_3)
//        val dao = MockUnlockedEntriesTrackerDAO(mutableListOf(
//            EntryIdentifier.ENTRY_1,
//            EntryIdentifier.ENTRY_3))
//        val expectedExceptions = setOf(
//            EntryAlreadyUnlockedException(EntryIdentifier.ENTRY_1),
//            EntryAlreadyUnlockedException(EntryIdentifier.ENTRY_3))
//
//        val service = UnlockedEntriesService(dao)
//
//        //when
//        val result = service.unlockMultipleEntries(entriesToUnlock)
//
//        //then
//        Assertions.assertTrue(
//            result.isLeft()
//                    && result.leftOrNull() is Set<EntryAlreadyUnlockedException>
//                    && result.leftOrNull()!!.map { exception -> exception.message }
//                        .containsAll(expectedExceptions.map { exception -> exception.message }),
//            "Result was not EntryAlreadyUnlockedException"
//        )
//    }

    @ParameterizedTest
    @MethodSource("unlockEntryParams")
    fun givenEntryNotUnlocked_whenCallIsEntryUnlocked_shouldReturnFalse(
        alreadyUnlockedEntries: MutableList<EntryIdentifier>
    ) {
        //given
        val entryToCheck = EntryIdentifier.ENTRY_1
        val dao = MockUnlockedEntriesTrackerDAO(alreadyUnlockedEntries)
        val service = UnlockedEntriesService(dao)

        //when
        val result = service.isEntryUnlocked(entryToCheck)

        //then
        Assertions.assertFalse(result)
    }

    @Test
    fun givenEntryUnlocked_whenCallIsEntryUnlocked_shouldReturnTrue() {
        //given
        val entryToCheck = EntryIdentifier.ENTRY_1
        val dao = MockUnlockedEntriesTrackerDAO(mutableListOf(EntryIdentifier.ENTRY_1))
        val service = UnlockedEntriesService(dao)

        //when
        val result = service.isEntryUnlocked(entryToCheck)

        //then
        Assertions.assertTrue(result)
    }
}