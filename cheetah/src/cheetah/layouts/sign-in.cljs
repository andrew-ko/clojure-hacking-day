(ns cheetah.layouts.sign-in
  (:require [rum.core :as rum]
            [cheetah.components.logo :as logo]))

(rum/defc SignIn [redirect]
  [:div.screen.signin
   (logo/Logo)
   [:form.signin
    [:input {:type "email" :placeholder "Username"}]
    [:button {:type "button" :on-click #(redirect "/main")} "Sign In"]]])

