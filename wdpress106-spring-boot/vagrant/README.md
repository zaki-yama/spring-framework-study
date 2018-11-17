第5章 本番環境で稼働させるために必要なこと
==========================================


## Usage

- Vitualbox, Vagrant をインストールする

```zsh
# Mac の場合
$ brew tap caskroom/cask
$ brew cask install virtualbox
$ brew cask install vagrant
```

- `todo/` , `qiita/` のサンプルアプリをビルドし、このディレクトリに配置する

```zsh
# Move to todo/ and build app
$ cd ../todo
$ ./gradlew clean build
$ mv build/libs/todo-0.0.1-SNAPSHOT.jar ../vagrant

# Move to qiita/ and build app
$ cd ../qiita
$ ./gradlew clean build
$ mv build/libs/qiita-0.0.1-SNAPSHOT.jar ../vagrant
```

- このディレクトリに戻り、Vagrant を起動する

```zsh
$ cd ../vagrant
$ vagrant up
```

- ブラウザで http://localhost:8080/todo/tasks, http://localhost:8080/qiita/items を開く
