import tkinter as tk
from tkinter import messagebox
import random

class CoinFlipApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Coin Flip-Flop Game")
        self.score = 0

        self.label = tk.Label(root, text="Guess the coin flip!", font=("Arial", 16))
        self.label.pack(pady=20)

        self.score_label = tk.Label(root, text=f"Score: {self.score}", font=("Arial", 14))
        self.score_label.pack(pady=10)

        self.heads_button = tk.Button(root, text="Heads", font=("Arial", 14), command=lambda: self.make_guess('Heads'))
        self.heads_button.pack(side=tk.LEFT, padx=20)

        self.tails_button = tk.Button(root, text="Tails", font=("Arial", 14), command=lambda: self.make_guess('Tails'))
        self.tails_button.pack(side=tk.RIGHT, padx=20)

        self.result_label = tk.Label(root, text="", font=("Arial", 14))
        self.result_label.pack(pady=20)

        self.quit_button = tk.Button(root, text="Quit", font=("Arial", 14), command=root.quit)
        self.quit_button.pack(pady=10)

    def flip_coin(self):
        return random.choice(['Heads', 'Tails'])

    def make_guess(self, guess):
        flip = self.flip_coin()
        self.result_label.config(text=f"The coin flip result is: {flip}")

        if guess == flip:
            self.score += 1
            messagebox.showinfo("Result", "Correct guess!")
        else:
            messagebox.showinfo("Result", "Incorrect guess.")

        self.score_label.config(text=f"Score: {self.score}")

if __name__ == "__main__":
    root = tk.Tk()
    app = CoinFlipApp(root)
    root.mainloop()
