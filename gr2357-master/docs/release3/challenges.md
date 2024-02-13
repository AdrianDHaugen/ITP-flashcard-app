# Challenges

### Tests for the rest module
*When writing tests for the rest module we encountered unforeseen challenges in dealing with the persistance of the application.*

When the rest service is called to get, modify or delete content it usually also calls its autoSaveModel() method. This presented a challenge as we do not want the tests to change the state of the application in any way compared to before the tests were ran. Therefore in its current state the tests in the rest module only tests the request that gets all Decks currently prescend in the system. Other tests are commented out for ease of access should we come upon a solution for this in the future.

The primary cause of this was an underestimation of the complexity of how to correctly set up Mockito to perform in the intended way. It is our understanding that Mockito would be able to "mock" the state changes made by the service in such a way that the actual files on disk are never modified. More specifically, mocking the "Context" object proved to not be as straight forward as mocking the service class, and as a result, test modifications are permanently saved. A further consequence of this is that we are also unable to properly test the "RestCommunicator" class leading to reported test coverage for both the Rest module and the FXUI module being lower that our specified standard.