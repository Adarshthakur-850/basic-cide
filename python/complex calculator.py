import tkinter as tk
import math

class ComplexCalculator:
    def __init__(self, root):
        self.root = root
        self.root.title("Complex Calculator")
        self.expression = ""
        self.input_text = tk.StringVar()

        input_frame = tk.Frame(self.root, bd=10, relief=tk.RIDGE)
        input_frame.pack(side=tk.TOP)

        input_field = tk.Entry(input_frame, font=('arial', 18, 'bold'), textvariable=self.input_text, width=28, bg="#eee", bd=0, justify=tk.RIGHT)
        input_field.grid(row=0, column=0)
        input_field.pack(ipady=10)

        btns_frame = tk.Frame(self.root, bg="grey")
        btns_frame.pack(expand=True, fill="both")

        self.add_buttons(btns_frame)

    def add_buttons(self, frame):
        button_texts = [
            '7', '8', '9', '/', 'C',
            '4', '5', '6', '*', '(',
            '1', '2', '3', '-', ')',
            '0', '.', '=', '+', 'CE',
            'sin', 'cos', 'tan', 'log', 'sqrt',
            '^', 'exp', 'mod', 'ln', '1/x'
        ]

        row_val = 0
        col_val = 0

        for i, button_text in enumerate(button_texts):
            button = tk.Button(frame, text=button_text, fg="black", font=('arial', 12, 'bold'), width=4, height=2, bd=0, bg="#fff", cursor="hand2", command=lambda txt=button_text: self.on_button_click(txt))
            button.grid(row=row_val, column=col_val, padx=1, pady=1, sticky="nsew")

            col_val += 1
            if col_val > 4:
                col_val = 0
                row_val += 1

        for i in range(5):
            frame.columnconfigure(i, weight=1)
        for i in range(6):
            frame.rowconfigure(i, weight=1)

    def on_button_click(self, button_text):
        if button_text == "C":
            self.expression = ""
            self.input_text.set(self.expression)
        elif button_text == "CE":
            self.expression = self.expression[:-1]
            self.input_text.set(self.expression)
        elif button_text == "=":
            try:
                result = self.evaluate_expression(self.expression)
                self.input_text.set(result)
                self.expression = result
            except:
                self.input_text.set("Error")
                self.expression = ""
        else:
            self.expression += button_text
            self.input_text.set(self.expression)

    def evaluate_expression(self, expression):
        try:
            expression = expression.replace('^', '**')
            if 'sin' in expression:
                expression = expression.replace('sin', 'math.sin')
            if 'cos' in expression:
                expression = expression.replace('cos', 'math.cos')
            if 'tan' in expression:
                expression = expression.replace('tan', 'math.tan')
            if 'log' in expression:
                expression = expression.replace('log', 'math.log10')
            if 'ln' in expression:
                expression = expression.replace('ln', 'math.log')
            if 'sqrt' in expression:
                expression = expression.replace('sqrt', 'math.sqrt')
            if 'exp' in expression:
                expression = expression.replace('exp', 'math.exp')
            if 'mod' in expression:
                expression = expression.replace('mod', '%')
            if '1/x' in expression:
                expression = expression.replace('1/x', '1/')
            
            result = eval(expression)
            return str(result)
        except Exception as e:
            return "Error"

if __name__ == "__main__":
    root = tk.Tk()
    root.geometry("400x500")
    ComplexCalculator(root)
    root.mainloop()
