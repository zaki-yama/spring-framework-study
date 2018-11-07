WEB+DB PRESS vol.106 速習 Spring Boot
=====================================

- Spring Boot の特徴
    - 実装はコンポーネント
        - DDD の影響を受けており、コンポーネントを意識した実装を行う
    - 設定はアノテーション
        - 従来は XML
    - 成果物はワンバイナリ
        - ビルドした成果物は1つのJARとしてアーカイブされる
        - ファイルサイズが大きくなるが、可搬性が高い

### 第2章

- コントローラー、サービス、リポジトリなどを「コンポーネント」とみなし、これらコンポーネントを組み合わせてアプリを作るイメージ
- Spring Data: データベース(Spring Boot では「データストア」として扱う) を操作するためのプロジェクト
- H2 Database(H2DB): MySQL, PostgreSQL などと同じく RDB 系のデータベース
- JPA: Java Persistence API の略。永続化に関する仕様
- Spring Data JPA: JPA の仕様をサポートした O/R マッピングフレームワーク

- ドメイン
    - DBとのやりとりを行うために用意するデータモデル
    - RDB 系のDBの場合、テーブルごとにクラスを用意し、 `@Entity` のアノテーションをクラスに付与する
    - コメント：「エンティティ」という言葉の方が直感的では？
- リポジトリ
    - ドメインをデータベースに永続化する処理を制御する
- サービス
    - ビジネスロジックを処理し、処理した結果を次に出てくるコントローラに届ける
- コントローラ
    - ユーザーのリクエストに応じてルーティングを行う

リポジトリ、サービス、コントローラは対応するアノテーションが提供されている。
クラスに対してアノテーションを付与することで、Spring Boot が自動的に Bean として登録する。
(従来の Spring Framework ではXML で行っていた部分)

#### テンプレートエンジン(Thymeleaf)

ビュー機能を実現するテンプレートエンジンとして
FreeMaker/Groovy/Thymeleaf/Mustache をサポートしている。

### 第3章

Gradle で依存追加

- org.webjars とは
    - クライアントサイドのライブラリを依存解決できるようにしたもの
        - [WebJars 使い方メモ - Qiita](https://qiita.com/opengl-8080/items/c8c5f787613c230a9827)
    - ので、バージョンの指定が必須

```
> Could not find org.webjars:bootstrap:.
  Required by:
      project :
```

### 第4章

`@SpringBootApplication` の内部は以下の3つのアノテーションを持っている

- `@EnableAutoConfiguration`
    - Spring Boot の自動設定項目を行うアノテーション
- `@ComponentScan`
    - `@Component` ( `@Controller / @Service / @Repository` など) の読み取りを行うアノテーション
- `@Configuration`
    - 設定を許可し、Bean を登録できるようにするアノテーション