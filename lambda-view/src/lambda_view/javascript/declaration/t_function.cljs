;; ESTree Spec
;; https://github.com/estree/estree

(ns lambda-view.javascript.declaration.t-function
  (:use [lambda-view.javascript.render :only [render-node]]
        [lambda-view.javascript.common :only [js-keyword
                                              white-space
                                              white-space-optional
                                              asterisk
                                              smart-box]]
        [lambda-view.tag :only [id-of]]))

;; FunctionDeclaration
(defn render [node]
  (let [generator (get node "generator")
        ;; [NOTE]
        ;; expression flag is such an old thing which is not supported anymore
        ;; check here: https://github.com/estree/estree/blob/master/deprecated.md#functions
        ;;             https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Operators/Expression_Closures
        ;; expression (get node "expression")
        async (get node "async")
        id (get node "id")
        params (get node "params")
        body (get node "body")]
    [:div.function.statement
     (if async (list (js-keyword "async")
                     (white-space)))
     (js-keyword "function")
     (white-space)
     (if generator (list (asterisk)
                         (white-space)))
     (if-not (nil? id) (list (render-node id)
                             (white-space-optional)))
     (smart-box {:id            (str (id-of node) ".params")
                 :pair          :parenthesis
                 :seperator     :comma
                 :init-collapse false
                 :init-layout   "horizontal"} params)
     (white-space-optional)
     (render-node body)]))


(def demo ["function a1() {}"
           "function a2(p2) {}"
           "function a3(u3,v4) {}"
           "async function a4() {}"
           "function *g1() {}"
           "async function *g2() {}"])