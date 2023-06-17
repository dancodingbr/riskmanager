# Risk Manager

This document describes briefly how to contribute to this project.

## How from do I start?

- Create a personal fork of the project on Github.

- Clone the fork on your local machine.

- Add the original repository as a remote called upstream.

```sh
git remote add upstream https://github.com/dancodingbr/riskmanager.git
```

- Verify if the new upstream repository was added correctly.

```sh
git remote -v

> origin    https://github.com/YOUR_USERNAME/YOUR_FORK.git (fetch)
> origin    https://github.com/YOUR_USERNAME/YOUR_FORK.git (push)
> upstream  https://github.com/dancodingbr/riskmanager.git (fetch)
> upstream  https://github.com/dancodingbr/riskmanager.git (push)
```

- If you created your fork a while ago be sure to pull upstream changes into your local repository.

```sh
git fetch upstream
git checkout main
git merge upstream/main
```

- Create a new branch to work on. For example:

```sh
git checkout -b feature/feature-name
```

- Explore the project's docs.

First, read the requirements document to understand what the application does. If the change that is proposed will affect the application domain, then all changes will need to be made firstly in the requirements doc. Consequently, the code changes must to be a reflection of what it was defined in the requirements. As needed, discuss the improvement with the team, in the issue related to this change or opening a new one, before starting these changes in the codebase.

- Prepare your development environment. See the project's wiki.

- Implement or fix the code.

- Follow the code style of the project, including indentation.

- Write and run the tests. Adapt them as needed.

- Update the documentation as needed.

- Add the changes.

```sh
git add .
```

- Commit the changes:

```sh
git commit -am "your_comment #<issue_number_reference>"
```

- Squash your commits into a single commit with git's interactive rebase.

```sh
git log
git checkout feature/feature-name
git rebase -i <hash-parent-commit>
git log --oneline
```

- Push your branch to your fork on Github, the remote origin.

```sh
git push --set-upstream origin feature/feature-name
```

- Create a new Pull Request.

- From your fork open a pull request in the correct branch.

- Once the pull request is approved and merged you can pull the changes from upstream to your local repo and delete your extra branch(es).

For additional information, see the project's [wiki](https://github.com/dancodingbr/riskmanager/blob/main/docs/wiki.md).
