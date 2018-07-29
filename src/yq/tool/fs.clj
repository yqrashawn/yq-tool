(ns yq.tool.fs
  (:require [me.raynes.fs :as fs]
            [clojure.java.io :as io]))

(defn expand-homes
  [path]
  (let [end-with-slash (clojure.string/ends-with? path "/")
        path (fs/expand-home path)]
    (if (= (type path) java.io.File)
      (if end-with-slash
        (str (.getPath path) "/")
        (.getPath path))
      path)))

(defn mkdirs
  "Make directory tree."
  [dir]
  (.mkdirs (io/file (expand-homes dir))))

(defn rm
  "Remove file or dirs"
  [path]
  (fs/delete (expand-homes path)))

(defn abs-file
  "Return absolute file."
  [path]
  (fs/absolute (expand-homes path)))

(defn exists?
  [path]
  (fs/exists? (expand-homes path)))

(defn directory?
  [path]
  (fs/directory? (expand-homes path)))

(defn write-file
  "Write INFO to file under PATH"
  [path info]
  (let [path (expand-homes path)
        parent-dir (.substring path 0 (.lastIndexOf path "/"))]
    (or (fs/exists? parent-dir) (fs/mkdirs parent-dir))
    (fs/touch path)
    (io/copy info (io/file (fs/expand-home path)))))