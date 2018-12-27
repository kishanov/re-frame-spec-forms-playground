(ns re-frame-spec-forms-doc.subs
  (:require
    [re-frame.core :as re-frame]

    [re-frame-spec-forms-doc.model :as model]))


(re-frame/reg-sub
  ::name
  (fn [db]
    (:name db)))


(re-frame/reg-sub
  ::state-dropdown-choices
  (fn []
    (->> model/state-abbrs
         (map (fn [e]
                (-> e
                    (clojure.set/rename-keys {:label :text :abbr :key})
                    (assoc :value (:abbr e)))))
         (sort-by :key))))
