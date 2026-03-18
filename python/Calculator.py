import tkinter as tk

class Calculator:
    def __init__(self, root):
        self.root = root
        self.root.title("Calculator")
        self.expression = ""
        self.input_text = tk.StringVar()

        input_frame = tk.Frame(self.root, bd=10, width=312, height=50, relief=tk.RIDGE)
        input_frame.pack(side=tk.TOP)

        input_field = tk.Entry(input_frame, font=('arial', 14, 'bold'), textvariable=self.input_text, width=24, bg="#eee", bd=0, justify=tk.RIGHT)
        input_field.grid(row=0, column=0)
        input_field.pack(ipady=10)

        btns_frame = tk.Frame(self.root, width=312, height=272.5, bg="grey")
        btns_frame.pack()

        self.add_buttons(btns_frame)

    def add_buttons(self, frame):
        button_texts = [
            '7', '8', '9', '/', 'C',
            '4', '5', '6', '*', '(',
            '1', '2', '3', '-', ')',
            '0', '.', '=', '+', 'CE'
        ]

        row_val = 0
        col_val = 0

        for button_text in button_texts:
            button = tk.Button(frame, text=button_text, fg="black", font=('arial', 14, 'bold'), width=6, height=2, bd=0, bg="#fff", cursor="hand2", command=lambda txt=button_text: self.on_button_click(txt))
            button.grid(row=row_val, column=col_val, padx=1, pady=1)

            col_val += 1
            if col_val > 4:
                col_val = 0
                row_val += 1

    def on_button_click(self, button_text):
        if button_text == "C":
            self.expression = ""
            self.input_text.set(self.expression)
        elif button_text == "CE":
            self.expression = self.expression[:-1]
            self.input_text.set(self.expression)
        elif button_text == "=":
            try:
                result = str(eval(self.expression))
                self.input_text.set(result)
                self.expression = result
            except:
                self.input_text.set("Error")
                self.expression = ""
        else:
            self.expression += button_text
            self.input_text.set(self.expression)

if __name__ == "__main__":
    root = tk.Tk()
    Calculator(root)
    root.mainloop()
