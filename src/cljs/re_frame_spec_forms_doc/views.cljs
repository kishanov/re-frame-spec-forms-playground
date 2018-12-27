(ns re-frame-spec-forms-doc.views
  (:require
    [re-frame.core :as re-frame]
    [re-frame-spec-forms.core :as spec-forms]

    [re-frame-spec-forms-doc.subs :as subs]
    [re-frame-spec-forms-doc.model :as model]

    [cljs.pprint :as pp]

    [soda-ash.core :as semantic-ui]
    [reagent.core :as reagent]))


(defn registration-form [form-key]
  (reagent/create-class
    {:component-did-mount
     #(re-frame/dispatch [::spec-forms/change-form-value form-key [] {:sex (first model/SEXES)}])

     :component-will-unmount
     #(re-frame/dispatch [::spec-forms/change-form-value form-key [] nil])

     :component-function
     (fn [form-key]
       (let [form-value @(re-frame/subscribe [::spec-forms/form-value form-key])]
         [:div
          [semantic-ui/Form
           [:h4.ui.dividing.header "Personal Info"]
           [:div.ui.section
            [:div.two.fields
             [semantic-ui/FormInput
              {:label     "First Name"
               :required  true
               :on-change (fn [_ data]
                            (re-frame/dispatch [::spec-forms/change-form-value form-key [:first-name] (.-value data)]))}]

             [semantic-ui/FormInput
              {:label     "Last Name"
               :required  true
               :on-change (fn [_ data]
                            (re-frame/dispatch [::spec-forms/change-form-value form-key [:last-name] (.-value data)]))}]]

            [:div.field
             [:label "Sex"]
             (->> model/SEXES
                  (map (fn [sex]
                         [:div.field
                          [semantic-ui/Radio
                           {:label     (clojure.string/capitalize sex)
                            :name      "sex-radio-group"
                            :value     sex
                            :checked   (= (:sex form-value) sex)
                            :on-change (fn [_ data]
                                         (js/console.log (.-value data))
                                         (re-frame/dispatch [::spec-forms/change-form-value form-key [:sex] (.-value data)]))}]]))
                  (into [:div.inline.fields]))]]

           [:h4.ui.dividing.header "Shipping Address"]
           [semantic-ui/FormInput
            {:label     "Address Line 1"
             :required  true
             :on-change (fn [_ data]
                          (re-frame/dispatch [::spec-forms/change-form-value form-key [:address :line1] (.-value data)]))}]

           [semantic-ui/FormInput
            {:label     "Address Line 2"
             :on-change (fn [_ data]
                          (re-frame/dispatch [::spec-forms/change-form-value form-key [:address :line2] (.-value data)]))}]

           [:div.fields
            [:div.six.wide.field
             [semantic-ui/FormInput
              {:label     "City"
               :required  true
               :on-change (fn [_ data]
                            (re-frame/dispatch [::spec-forms/change-form-value form-key [:address :city] (.-value data)]))}]]

            [:div.six.wide.field
             [:label "State"]
             [semantic-ui/Dropdown
              {:placeholder "State"
               :search      true
               :required    true
               :selection   true
               :clearable   true
               :options     @(re-frame/subscribe [::subs/state-dropdown-choices])
               :on-change   (fn [_ data]
                              (re-frame/dispatch [::spec-forms/change-form-value form-key [:address :state] (.-value data)]))}]]

            [:div.four.wide.field
             [semantic-ui/FormInput
              {:label     "ZIP"
               :required  true
               :on-change (fn [_ data]
                            (let [raw-input (.-value data)
                                  new-value (when (re-matches #"\d+" raw-input)
                                              (int raw-input))]
                              (re-frame/dispatch [::spec-forms/change-form-value form-key [:address :zip] new-value])))}]]]]]))}))



(defn main-panel []
  (let [form-key ::model/registration-form]
    [semantic-ui/Container
     [:h1.ui.header "Registration Form"]
     [:div.ui.grid
      [:div.row
       [:div.eight.wide.column
        [registration-form form-key]]

       [:div.four.wide.column
        [:pre
         [:code
          (str (with-out-str (pp/pprint @(re-frame/subscribe [::spec-forms/form-value form-key]))))]]]]]]))

