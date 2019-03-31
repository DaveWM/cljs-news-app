(ns cljs-news-app.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::articles
 (fn [db]
   (:articles db)))

(re-frame/reg-sub
  ::loading?
  (fn [db]
    (not (zero? (:loading db)))))
