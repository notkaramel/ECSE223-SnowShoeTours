# :snowflake::ski: ECSE223 SnowShoeTours Project: Team 07

## Project Overview
For more information about the SnowShoeTours application, please consult the [wiki](../../wiki).

More details on [Iteration 2 Instruction](https://github.com/McGill-ECSE223-W23/ecse223-tutorials/wiki/Technical-Instructions-for-Group-Project-Iteration-2)

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
# Switch

```

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

## Team Members

| Name          | GitHub username |
| ------------- | --------------- |
| Angela Zhu    | [angelaxzhu](https://github.com/angelaxzhu) |
| Antoine Phan  | [notkaramel](https://github.com/notkaramel) |
| Bilar Mokhtari| [bmokhtari](https://github.com/bmokhtari)   |
| Emma Friesen  | [emma-friesen](https://github.com/emma-friesen) |
| Jennifer Tram Su | [jennifertramsu](https://github.com/jennifertramsu) |
| Sameer Riaz   | [SRIAZ77](https://github.com/SRIAZ77) |
