(ns re-frame-spec-forms.core
  (:require [re-frame.core :as rf]
            [cljs.spec.alpha :as s]))


(def db-path [::spec-forms])


(rf/reg-sub
  ::spec-forms
  (fn [db]
    (get-in db db-path)))


(defn- field-value-path [form-key & [field-path]]
  (vec (concat [form-key ::value]
               (or field-path []))))


(defn get-field-value [forms [_ form-key field-path]]
  (get-in forms (field-value-path form-key field-path)))


(rf/reg-sub
  ::form-value
  :<- [::spec-forms]
  get-field-value)


(rf/reg-sub
  ::form-valid?
  :<- [::spec-forms]
  (fn [forms [_ form-key field-path]]
    (s/valid? form-key (get-in forms (field-value-path form-key field-path)))))


(rf/reg-event-db
  ::change-form-value
  (fn [db [_ form-key field-path new-value]]
    (assoc-in db
              (vec (concat db-path (field-value-path form-key field-path)))
              new-value)))



