(ns generative-art.the-wrong-way-to-draw-circle
  (:require [quil.core :as q]
            [quil.middleware :as m]))

(defn draw-line [points]
  (doseq [p (partition 2 1 points)]
    (let [[{x1 :x, y1 :y} {x2 :x, y2 :y}] p]
      (q/line x1 y1 x2 y2))))

(defn setup []
  (q/background 255)
  (q/stroke-weight 5)
  (q/smooth)

  ;(q/stroke 0 30)
  ;(q/ellipse 250 150 200 200)

  (q/stroke 20 50 70)

  (let [center-x (/ (q/width) 2)
        center-y (/ (q/height) 2)
        radiuses (iterate #(+ % 0.5) 10)
        angles (range 0 1441 5)
        rads (map q/radians angles)
        make-point (fn [radius rad]                     
                     {:x (+ center-x (* (q/cos rad) radius))
                      :y (+ center-y (* (q/sin rad) radius))})
        points (map make-point radiuses rads)]
    (draw-line points)))

(comment 
  )

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