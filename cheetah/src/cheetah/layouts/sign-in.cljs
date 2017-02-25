(ns cheetah.layouts.sign-in
  (:require [rum.core :as rum]
            [cheetah.components.logo :as logo]
            [cheetah.localstorage :as storage]))

(defonce username (atom))

(defn handle-change [e]
  (reset! username e.target.value))

(defn handle-submit [redirect, username]
  (storage/set-item! "name" username)
  (redirect "/main"))

(rum/defc SignIn [redirect]
  [:div.screen.signin
   (logo/Logo)
   [:form.signin
    [:input {:type "email" :placeholder "Username" :on-change #(handle-change %)}]
    [:button {:type "button" :on-click #(handle-submit redirect @username)} "Sign In"]]])

