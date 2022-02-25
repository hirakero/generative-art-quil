(ns generative-art.the-wrong-way-to-draw-circle
  (:require [quil.core :as q]
            [quil.middleware :as m]))



(defn setup []
  (q/background 255)
  (q/stroke-weight 5)
  (q/smooth)

  ;(q/stroke 0 30)
  ;(q/ellipse 250 150 200 200)

  (q/stroke 20 50 70)
  (let [radius 100
        center-x (/ (q/width) 2)
        center-y (/ (q/height) 2)]
    (doseq [angle (range 0 361)]
      (let [rad (q/radians angle)
            x (+ center-x (* radius (q/cos rad)))
            y (+ center-y (* radius (q/sin rad)))]
        (q/point x y)))))

(defn update-state [state])

(defn draw-state [state])

(q/defsketch generative-art
  :title "the wrong way to draw a circle"
  :size [500 300]
  :setup setup
  :update update-state
  :draw draw-state
  :features [:keep-on-top]
  :middleware [m/fun-mode])