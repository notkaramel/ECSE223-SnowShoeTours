# :snowflake::ski: ECSE223 SnowShoeTours Project: Team 07

## Project Overview
For more information about the SnowShoeTours application, please consult the [wiki](../../wiki).

More details on [Iteration 2 Instruction](https://github.com/McGill-ECSE223-W23/ecse223-tutorials/wiki/Technical-Instructions-for-Group-Project-Iteration-2)

## Team Members

| Name          | GitHub username |
| ------------- | --------------- |
| Angela Zhu    | [angelaxzhu](https://github.com/angelaxzhu) |
| Antoine Phan  | [notkaramel](https://github.com/notkaramel) |
| Bilar Mokhtari| [bmokhtari](https://github.com/bmokhtari)   |
| Emma Friesen  | [emma-friesen](https://github.com/emma-friesen) |
| Jennifer Tram Su | [jennifertramsu](https://github.com/jennifertramsu) |
| Sameer Riaz   | [SRIAZ77](https://github.com/SRIAZ77) |

## Command-line nagivation basics
### The "terminal"
- On Windows: please use **PowerShell** or **Git Bash** (or maybe `PuTTY` :\\)
- On Linux: please use the terminal emulator of choice (e.g., Gnome's built-in `gnome-terminal`, `alacritty`, `kitty`). Any shells (`bash`, `zsh`, `fish` would work).
- On MacOS: please use the built-in terminal emulator or your terminal of choice.

### File navigation
- Usually your current folder will be shown on the same line to the left (or a line above) of your cursor. Else, you can check with
```sh
pwd
```

- To list all files & folders in your current location, use `ls`. Other flags might apply such as:
```sh
ls	# to list all files & folders
ls -a	# to list all files & folders (includes hidden ones)
ls -l 	# to list files and folders with their permission on your machine.
```

- To change directory, use `cd`. Examples:
```sh
cd Desktop/ecse223-project	# To access ecse223-project that you put on the Desktop
cd ..		# To go to the parent folder of your current location
cd		# To go to your home folder (Linux & MacOS tested, not sure how Windows would work)
```

- Check if prerequisite programs available on your machine:
```sh
git -v 		# to check if git is installed
java -version	# to check java version (we just JDK 17 or 19)
```

## Checkout to your own branch
```bash
# Create a new branch
git checkout -b branch-name # replace branch-name with your own branch name

# For example:
git checkout -b emma
```

## Switch/Checkout to an exisiting branch
```bash
git checkout <branch name>	# or, on some machines
git switch <branch name>


# For example
git checkout main	# switch to main branch
git checckout emma	# switch to branch named "Emma"
```

## Merging branches & Resolving Conflicts (might use `Vim` editor)
- Cases where you want to merge branch **in this project specifically** are:
	1. You want to get updates from `common-base` branch to the `main` branch.
	2. You want to get updates from the `main` branch to your own working branch.
	3. (Please do Pull Request on GitHub instead) You want to apply your changes from your own branch to the GitHub repository. E.g., you merge your branch `emma` to the `development` branch.
- And please do not merge anything into `common-base` branch
- How to:

```sh
# Switch to your target branch
git checkout <your-target-branch>

# Merge to your target branch from your reference branch
git merge <your-reference-branch>
```

- At this step, you may encounter a pop-up window asking you to write/edit a merge message, and it will be [by default] use a text editor called VIM. If you know how to use VIM, amazing! Else, no need to worry. The message has already written for you by default. All you have to do is type the following sequence: (see the lower left corner of your terminal window)
```
:wq
```
- Merge conflicts may occurs. In this case, it's recommended that you resolves conflicts on editor such as VSCode, by either choosing to keep the current changes or apply the incoming changes to your branch.

- Further instruction on this will be updated for ECSE 321 I guess :))


## Common commands for Git
>Make sure that you work on your branch and not on the `main` branch.

```bash
# Check the status of the repository.
# Attention to your current branch and files you modified/added.
# Attention to whether your branch(es) is behind/ahead of the GitHub repo.
git status

# Update the repository from remote repository
git pull

# Add all changes to the staging area
git add .  # or
git add -A
# Add a specific file to the staging area 
git add <file>

# Commit the changes in the staging area
git commit -m "Commit message"

# Push the changes to the remote repository
git push
```


