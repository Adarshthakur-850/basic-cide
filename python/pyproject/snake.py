import tkinter as tk
import random

class SnakeGame:
    def __init__(self, root):
        self.root = root
        self.root.title("Snake Game")

        self.canvas = tk.Canvas(root, bg="black", height=400, width=600)
        self.canvas.pack()

        self.snake = [(20, 20), (20, 30), (20, 40)]
        self.snake_dir = "Down"
        self.food = self.place_food()

        self.score = 0
        self.game_over = False

        self.root.bind("<KeyPress>", self.change_direction)
        self.update_snake()
        self.run_game()

    def place_food(self):
        while True:
            x = random.randint(0, 29) * 20
            y = random.randint(0, 19) * 20
            if (x, y) not in self.snake:
                return (x, y)

    def change_direction(self, event):
        directions = {"Up": "Down", "Down": "Up", "Left": "Right", "Right": "Left"}
        new_dir = event.keysym
        if new_dir in directions and directions[new_dir] != self.snake_dir:
            self.snake_dir = new_dir

    def update_snake(self):
        if self.game_over:
            self.canvas.create_text(300, 200, text="Game Over", fill="red", font=("Arial", 24))
            self.canvas.create_text(300, 240, text=f"Score: {self.score}", fill="white", font=("Arial", 18))
            return

        head_x, head_y = self.snake[-1]
        if self.snake_dir == "Up":
            head_y -= 20
        elif self.snake_dir == "Down":
            head_y += 20
        elif self.snake_dir == "Left":
            head_x -= 20
        elif self.snake_dir == "Right":
            head_x += 20

        new_head = (head_x, head_y)
        if new_head in self.snake or head_x < 0 or head_x >= 600 or head_y < 0 or head_y >= 400:
            self.game_over = True
            self.update_snake()
            return

        self.snake.append(new_head)
        if new_head == self.food:
            self.food = self.place_food()
            self.score += 1
        else:
            self.snake.pop(0)

        self.canvas.delete(tk.ALL)
        self.canvas.create_rectangle(self.food[0], self.food[1], self.food[0] + 20, self.food[1] + 20, fill="red")
        for segment in self.snake:
            self.canvas.create_rectangle(segment[0], segment[1], segment[0] + 20, segment[1] + 20, fill="green")

    def run_game(self):
        self.update_snake()
        if not self.game_over:
            self.root.after(100, self.run_game)


if __name__ == "__main__":
    root = tk.Tk()
    game = SnakeGame(root)
    root.mainloop()
