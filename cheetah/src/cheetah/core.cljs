(ns cheetah.core
  (:require [rum.core :as rum]
            [bide.core :as r]
            [pushy.core :as pushy]
            [cheetah.layouts.sign-in :as sign-in]
            [cheetah.layouts.main :as main]))

(enable-console-print!)


(def route (atom))

(def router
  (r/router [["/" :sign-in]
             ["/main" :main]]))

(def history
  (pushy/pushy #(reset! route %) (partial r/match router)))

(pushy/start! history)


(defn redirect [path]
  (pushy/set-token! history path))


(rum/defc Router < rum/reactive [route]
  (let [[handler] (rum/react route)]
    [:div
     (case handler
       :sign-in (sign-in/SignIn redirect)
       :main (main/Main)
       nil)]))

(rum/mount (Router route)
           (. js/document (getElementById "app")))
