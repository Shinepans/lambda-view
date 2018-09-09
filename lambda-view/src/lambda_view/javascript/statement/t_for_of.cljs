;; ESTree Spec
;; https://github.com/estree/estree

(ns lambda-view.javascript.statement.t-for-of
  (:use [lambda-view.javascript.render :only [render-node]]
        [lambda-view.javascript.common :only [js-keyword
                                              white-space
                                              white-space-optional
                                              semicolon
                                              smart-box]]
        [lambda-view.tag :only [id-of]]))

;; ForOfStatement
(defn render [node]
  (let [id (id-of node)
        left (get node "left")
        right (get node "right")
        body (get node "body")]
    [:div {:class "for-of statement"}
     (js-keyword "for")
     (white-space-optional)
     (smart-box {:id            id
                 :pair          :parenthesis
                 :seperator     :none
                 :init-collapse false
                 :init-layout   "horizontal"
                 :auto-render   false} (list (render-node left)
                                             (white-space) (js-keyword "of") (white-space)
                                             (render-node right)))
     (white-space-optional)
     (render-node body)]))

(def demo ["for(a of b) 1"
           "for(a of b) {1}"
           ;; TODO "for await (const x of xs) {}"
           ])
