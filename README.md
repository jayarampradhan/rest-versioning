Why?
===================
When developing api initially, its not mandatory to have all the implementation and logic at first place due to 
various constraint. its logical to keep evolving api defination, addition of new api, change in parameter`(Query, 
Path, Form etc)` and removal of deprecated methods or API. Which is always better to expose the changes in a new 
version rather than breaking the clients who are using it.
     
How?
====================
As there is no proper specification been there to version API, below are some points kept in mind while implementing 
the solution:
  
  * As our new API will not have whole set of new code, so re-usability should be first preference.
  * As we don't know if all client will plan for the change as per our release date, lets keep URL safe.
  * Over the time, we can't keep supporting all the version's so lets plan out for decommission of very old version. 
  * Client On-boarding to new version. 
  
# Approaches
## Extending Resources
### Concept

- To have code re-usability, we can extend the base resource class, and change the base path in the child to point to 
the new version, override the methods which has changed the functionality.

### Challenges

- All works well, but when we come across problem of method overloading, for example in new api we are introducing 
some new Query Parameter or request body payload, then we will end up with duplicate resource issue. 
As inherited method will have same path as of overloaded method which will end up into issue.
So Clearly its not a solution.
    
    