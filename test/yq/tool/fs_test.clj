(ns yq.tool.fs-test
  (:require [yq.tool.fs :as sut]
            [me.raynes.fs :as fs]
            [clojure.test :as t]))

(t/deftest mkdirs-test
  (t/testing "FIXME, mkdirs fail."
    (t/is (sut/mkdirs "~/yqt-fs-test/mkdirs/test"))
    (t/is (not (sut/mkdirs "~/yqt-fs-test/mkdirs/test")))
    (t/is (fs/exists? (fs/expand-home "~/yqt-fs-test/mkdirs/test")))))

(t/deftest rm-test
  (t/testing "FIXME, rm fail."
    (t/is (sut/rm "~/yqt-fs-test/mkdirs/test"))
    (t/is (not (fs/exists? (fs/expand-home "~/yqt-fs-test/mkdirs/test"))))))