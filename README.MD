# Escape!

## Building
Building is straight forward. Open up the project in Eclipse Neon and hit launch.

## Submitting changes
To submit changes, you must first do the following:

1. Stash any changes and type _git checkout master_
2. Type _git branch branchname_, where _branchname_ is the name of your branch
3. Create any changes, i.e new classes, functions. 
4. _git add_ all of the files you want added to the commit.
5. Run _git commit_ and enter your commit message (Title first line, two newline characters then the description)
6. Run _git push -u origin branchname_ where _branchname_ is the name of the branch you want to push to.
7. Open a pull request on your github. Here we can review and make necessary changes before it is accepted into the master branch.

## Keeping your fork up to date

Firstly, you want to add an upstream remote by typing _git remote add upstream https://www.github.com/quaker762/escape_. This only needs to be performed ONCE!

1. Run _git checkout master_
2. Now, run _git fetch --all_
3. Finally, run _git pull upstream/master_ to fetch from the master branch. 

You can now create new branches. Old branches don't need to be updated (for the most part), to a. avoid conflicts and b. because they will be pulled into master. 