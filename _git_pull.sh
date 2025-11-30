# put this file one folder up to git-controlled
cd crudio

git reset --hard

git checkout develop
git pull

chmod +x ./*.sh
chmod +x ./backend/*.sh

cd ..