# Module api

This module is meant to be used as the main gateway into the application. The appropriate packages contain command
and query APIs that can be used to either tell the game what to do and mutate its state (command) or retrieve
information (query).

# Package com.github.rskupnik.thestory.api

This is the main package of the API.  

# Package com.github.rskupnik.thestory.api.command

This is where the API for issuing commands can be found - the code here mutates the state of the game.

# Package com.github.rskupnik.thestory.api.query

This is where the API for issuing queries and retrieving information can be found. These do not mutate the state.

# Package com.github.rskupnik.thestory.api.init

This subpackage contains some initialization code and should not be used by any higher level functionality.
