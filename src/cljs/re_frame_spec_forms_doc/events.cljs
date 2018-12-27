(ns re-frame-spec-forms-doc.events
  (:require
    [re-frame.core :as re-frame]
    [re-frame-spec-forms-doc.db :as db]
    [day8.re-frame.tracing :refer-macros [fn-traced defn-traced]]))


(re-frame/reg-event-db
  ::initialize-db
  (fn-traced [_ _]
             db/default-db))
