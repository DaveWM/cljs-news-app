(ns cljs-news-app.views
  (:require
    [re-frame.core :as re-frame]
    [cljs-news-app.subs :as subs]
    [cljs-news-app.events :as events]
    ))

(defn main-panel []
  (let [articles (re-frame/subscribe [::subs/articles])
        loading? (re-frame/subscribe [::subs/loading?])]
    [:div.container
     [:h1 "ClojureScript News App"]
     [:input.form-control {:type        "text"
                           :placeholder "Type your search query here..."
                           :on-change   #(re-frame/dispatch [::events/search-query-updated (.-value (.-target %))])}]
     (when @loading?
       [:div.spinner-container
        [:div.spinner-grow.text-light.loading-spinner]])
     [:div (->> @articles
                (map (fn [a] [:div.media.article {:class (when @loading? "loading")}
                              [:img.mr-3.align-self-center {:src (or (:urlToImage a)
                                                                     "https://via.placeholder.com/80")}]
                              [:div.media-body
                               [:h5.mt-0 (:title a)]
                               [:p (:description a)]
                               [:a {:href (:url a)} "Read More..."]]])))]]))
