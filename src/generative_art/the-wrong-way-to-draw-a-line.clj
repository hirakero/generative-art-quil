(ns generative-art.the-wrong-way-to-draw-a-line
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn setup-random1 []
  (q/frame-rate 30)
  ;(q/color-mode :hsb)
  (q/background 255)
  (q/stroke-weight 1)
  (q/smooth)

  (q/stroke 20 50 70)

  (doseq [p (partition 2 1 (for [x (range 500)]
                             {:x x :y (+ 50 (rand-int 100))}))]
    (let [f (first p)
          s (second p)]
      (q/line (:x f)  (:y f)  (:x s)  (:y s)))))

(defn setup-random2 []
  (q/frame-rate 30)
  ;(q/color-mode :hsb)
  (q/background 255)
  (q/stroke-weight 1)
  (q/smooth)

  (q/stroke 20 50 70)

  (doseq [p (partition 2 1 (reduce (fn [col n]
                                     (let [l (last col)
                                           y (+ (:y l) (- (rand-int 20) 9.5))]
                                       (conj col {:x n :y y})))
                                   [{:x 0 :y (/ (q/height) 2)}] (range (q/width))))]
    (let [f (first p)
          s (second p)]
      (q/line (:x f)  (:y f)  (:x s)  (:y s)))))



(defn update-state [state])

(defn draw-state [state])


(q/defsketch generative-art
  :title "the wrong way to draw a line"
  :size [500 500]
  :setup setup-random2
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])