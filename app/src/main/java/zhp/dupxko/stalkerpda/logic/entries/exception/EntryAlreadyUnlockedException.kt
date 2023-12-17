package zhp.dupxko.stalkerpda.logic.entries.exception

import zhp.dupxko.stalkerpda.logic.entries.model.EntryIdentifier

class EntryAlreadyUnlockedException(identifier: EntryIdentifier)
    //this thing below is a primary constructor which passes predefined message to super
    : Exception(
        String.format(
            "Entry [%s] is already unlocked",
            identifier
        )
    )
{}