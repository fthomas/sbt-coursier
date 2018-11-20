package coursier.sbtcoursier

import java.io.File
import java.net.URL

import coursier.{Cache, CachePolicy, Credentials, FileError, ProjectCache}
import coursier.core._
import sbt.librarymanagement.{GetClassifiersModule, Resolver}
import sbt.{InputKey, SettingKey, TaskKey}

import scala.concurrent.duration.Duration

object Keys {
  val coursierParallelDownloads = SettingKey[Int]("coursier-parallel-downloads")
  val coursierMaxIterations = SettingKey[Int]("coursier-max-iterations")
  val coursierChecksums = SettingKey[Seq[Option[String]]]("coursier-checksums")
  val coursierArtifactsChecksums = SettingKey[Seq[Option[String]]]("coursier-artifacts-checksums")
  val coursierCachePolicies = SettingKey[Seq[CachePolicy]]("coursier-cache-policies")
  val coursierTtl = SettingKey[Option[Duration]]("coursier-ttl")
  val coursierCreateLogger = TaskKey[() => Cache.Logger]("coursier-create-logger")

  val coursierVerbosity = SettingKey[Int]("coursier-verbosity")

  val mavenProfiles = SettingKey[Set[String]]("maven-profiles")

  val coursierSbtResolvers = TaskKey[Seq[Resolver]]("coursier-sbt-resolvers")
  val coursierUseSbtCredentials = SettingKey[Boolean]("coursier-use-sbt-credentials")
  val coursierCredentials = TaskKey[Map[String, Credentials]]("coursier-credentials")

  val coursierCache = SettingKey[File]("coursier-cache")

  val coursierFallbackDependencies = TaskKey[Seq[(Module, String, URL, Boolean)]]("coursier-fallback-dependencies")

  val coursierConfigGraphs = TaskKey[Seq[Set[Configuration]]]("coursier-config-graphs")

  val coursierSbtClassifiersModule = TaskKey[GetClassifiersModule]("coursier-sbt-classifiers-module")

  val coursierConfigurations = TaskKey[Map[Configuration, Set[Configuration]]]("coursier-configurations")


  val coursierParentProjectCache = TaskKey[Map[Seq[Resolver], Seq[ProjectCache]]]("coursier-parent-project-cache")
  val coursierResolutions = TaskKey[Map[Set[Configuration], Resolution]]("coursier-resolutions")

  private[coursier] val actualCoursierResolution = TaskKey[Resolution]("coursier-resolution")

  val coursierSbtClassifiersResolution = TaskKey[Resolution]("coursier-sbt-classifiers-resolution")

  val coursierDependencyTree = TaskKey[Unit](
    "coursier-dependency-tree",
    "Prints dependencies and transitive dependencies as a tree"
  )
  val coursierDependencyInverseTree = TaskKey[Unit](
    "coursier-dependency-inverse-tree",
    "Prints dependencies and transitive dependencies as an inverted tree (dependees as children)"
  )

  val coursierWhatDependsOn = InputKey[String](
    "coursier-what-depends-on",
    "Prints dependencies and transitive dependencies as an inverted tree for a specific module (dependees as children)"
  )
  val coursierArtifacts = TaskKey[Map[Artifact, Either[FileError, File]]]("coursier-artifacts")
  val coursierSignedArtifacts = TaskKey[Map[Artifact, Either[FileError, File]]]("coursier-signed-artifacts")
  val coursierClassifiersArtifacts = TaskKey[Map[Artifact, Either[FileError, File]]]("coursier-classifiers-artifacts")
  val coursierSbtClassifiersArtifacts = TaskKey[Map[Artifact, Either[FileError, File]]]("coursier-sbt-classifiers-artifacts")
}
