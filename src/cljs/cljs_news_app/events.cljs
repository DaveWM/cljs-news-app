(ns cljs-news-app.events
  (:require
    [re-frame.core :as re-frame]
    [cljs-news-app.db :as db]
    [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]
    [day8.re-frame.http-fx]
    [clojure.string :as s]
    [ajax.core :as ajax]))

(re-frame/reg-event-db
  ::initialize-db
  (fn-traced [_ _]
    db/default-db))


(re-frame/reg-event-fx
  ::search-query-updated
  (fn-traced [{:keys [db]} [_ query]]
    (if (s/blank? query)
      {:db db}
      {:db         (-> db
                       (assoc :search-query query)
                       (update :loading inc))
       :http-xhrio {:method          :get
                    :uri             (str "https://newsapi.org/v2/everything?q=" query "&apiKey=a35ce68466704851bec15046387412f6")
                    :response-format (ajax/json-response-format {:keywords? true})
                    :on-success      [::response-received]}})))


(re-frame/reg-event-db
  ::response-received
  (fn-traced [db [_ response]]
    (-> db
        (assoc :articles (:articles response))
        (update :loading dec))))
