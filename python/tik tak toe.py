import tkinter as tk
from tkinter import messagebox

class TicTacToe:
    def __init__(self, root):
        self.root = root
        self.root.title("Tic Tac Toe")
        self.current_player = "X"
        self.buttons = [[None for _ in range(3)] for _ in range(3)]
        self.create_buttons()

    def create_buttons(self):
        for row in range(3):
            for col in range(3):
                self.buttons[row][col] = tk.Button(self.root, text=" ", font='normal 20 bold', height=3, width=6,
                                                   command=lambda r=row, c=col: self.on_button_click(r, c))
                self.buttons[row][col].grid(row=row, column=col)

    def on_button_click(self, row, col):
        if self.buttons[row][col]["text"] == " ":
            self.buttons[row][col]["text"] = self.current_player
            if self.check_win(self.current_player):
                messagebox.showinfo("Tic Tac Toe", f"Player {self.current_player} wins!")
                self.reset_board()
            elif self.check_draw():
                messagebox.showinfo("Tic Tac Toe", "It's a draw!")
                self.reset_board()
            else:
                self.current_player = "O" if self.current_player == "X" else "X"

    def check_win(self, player):
        # Check rows, columns and diagonals for a win
        for row in range(3):
            if all(self.buttons[row][col]["text"] == player for col in range(3)):
                return True
        for col in range(3):
            if all(self.buttons[row][col]["text"] == player for row in range(3)):
                return True
        if all(self.buttons[i][i]["text"] == player for i in range(3)):
            return True
        if all(self.buttons[i][2-i]["text"] == player for i in range(3)):
            return True
        return False

    def check_draw(self):
        return all(self.buttons[row][col]["text"] != " " for row in range(3) for col in range(3))

    def reset_board(self):
        for row in range(3):
            for col in range(3):
                self.buttons[row][col]["text"] = " "
        self.current_player = "X"

if __name__ == "__main__":
    root = tk.Tk()
    game = TicTacToe(root)
    root.mainloop()
