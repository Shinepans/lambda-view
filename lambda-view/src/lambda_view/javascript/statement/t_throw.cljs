;; ESTree Spec
;; https://github.com/estree/estree

(ns lambda-view.javascript.statement.t-throw
  (:require [lambda-view.utils :as utils])
  (:use [lambda-view.javascript.render :only [render-node
                                            render-node-coll]]
        [lambda-view.javascript.common :only [js-keyword
                                   white-space
                                   white-space-optional
                                   asterisk
                                   comma
                                   equal
                                   common-list
                                   collapsable-box]]
        [lambda-view.tag :only [id-of]]
        [lambda-view.state :only [init-collapse!
                                  init-layout!]]))

;; ThrowStatement
(defn render [node]
  (let [argument (get node "argument")]
    [:div {:class "throw statement"}
     (js-keyword "throw") (white-space) (render-node argument)]))