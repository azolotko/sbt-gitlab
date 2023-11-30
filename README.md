# sbt-gitlab

GitLab dependency resolution and artifact publishing for sbt.

## Requirements

This plugin requires sbt 1.5.0+.

## Usage

Add the following to your project/plugins.sbt file:

```scala
addSbtPlugin("nl.zolotko.sbt" % "sbt-gitlab" % "0.1.2")
```

You can configure the plugin in your build.sbt file by overriding `gitlabDomain` (default is `"gitlab.com"`):

```scala
gitlabDomain := "gitlab.your-company.com"
```

### Credentials

You can either put your credentials directly into build.sbt (not recommended):

```scala
gitlabCredentials := GitlabCredentials(gitlabDomain.value, "Private-Token", "<ACCESS-TOKEN>").some
```

Or, keep them out of your source control:

> ~/.sbt/1.0/.credentials

```.credentials
realm=gitlab
host=gitlab.your-company.com
user=Private-Token
password=<ACCESS-TOKEN>
```

> build.sbt or ~/.sbt/1.0/credentials.sbt

```scala 
DefaultOptions.addCredentials
```

### Dependency Resolution

This plugin supports dependency resolution from multiple GitLab package repositories:

```sbt
gitlabRepositories ++= Seq(
  GitlabProjectRepository(gitlabDomain.value, GitlabProjectId("unicorn")),
  GitlabGroupRepository(gitlabDomain.value, GitlabGroupId("ninjas"))
)
```

### Publishing from GitLab CI/CD

Doing `sbt publish` from GitLab CI/CD should require no additional configuration.
This plugin automatically pulls the following GitLab environment variables provided by GitLab Pipelines:

```shell
$CI_JOB_TOKEN   # Access Token to authorize read/writes to the GitLab package registry
$CI_PROJECT_ID  # Project ID for active project pipeline. Used so GitLab knows what project to publish the artifact under
$CI_GROUP_ID    # GitLab Group ID. Used for fetching Artifacts published under the specified Group. 
                # In a pipeline this would be set to the id of the group the project is under (when applicable)
$CI_SERVER_HOST # The host name for GitLab defaults to gitlab.com
```

### Publishing outside GitLab CI/CD

For `sbt publish` to work outside GitLab CI/CD, at least `gitlabDomain` and `gitlabProjectId` need to be defined:

```sbt
gitlabDomain := "gitlab.your-company.com"
gitlabProjectId := GitlabProjectId("unicorn").some
```

(remember to set up the [Credentials](#Credentials))

### Testing

Run `test` for regular unit tests (there are none at the moment).

Run `scripted` for [sbt script tests](http://www.scala-sbt.org/1.x/docs/Testing-sbt-plugins.html).

### Credits

This plugin is a fork of [gilcloud/sbt-gitlab](https://github.com/gilcloud/sbt-gitlab).
