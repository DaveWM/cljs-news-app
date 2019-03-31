(ns cljs-news-app.css
  (:require [garden.def :refer [defstyles]]))

(defstyles screen
           [:body {:color            "white"
                   :background-color "black"
                   :height           "100%"}]
           [:h1 {:text-align "center"
                 :margin-top "30px"
                 :margin-bottom "30px"}]
           [:html {:height "100%"}]

           [:.article {:text-align    "left"
                       :margin-top    "20px"
                       :margin-bottom "20px"
                       :transition "0.5s ease opacity"}
            [:&.loading {:opacity "0.3"}]
            [:img {:width  "70px"
                   :height "70px"
                   :object-fit "cover"
                   :object-position "center"
                   :border-radius "8px"}]
            [:p {:margin-bottom "0px"}]]

           [:.spinner-container {:position "relative"}]
           [:.loading-spinner {:width "40px"
                               :height "40px"
                               :position :absolute
                               :left "50%"
                               :top "20px"}]
           )
