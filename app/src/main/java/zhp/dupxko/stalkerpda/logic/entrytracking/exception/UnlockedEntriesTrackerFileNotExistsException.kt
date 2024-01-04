package zhp.dupxko.stalkerpda.logic.entrytracking.exception

class UnlockedEntriesTrackerFileNotExistsException :
    //this thing below is a primary constructor which passes predefined message to super
    Exception("Tracking file doesn't exist (but it should)")