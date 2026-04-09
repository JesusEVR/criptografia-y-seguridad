import base64
from Ejercicio6 import des_decrypt

def decrypt_playfair(ciphertext, key):
    # Matriz de playfair
    alphabet = "ABCDEFGHIKLMNOPQRSTUVWXYZ" # Sin J ni Ñ
    matrix_str = ""
    
    # Agrego clave sin repetir las letras
    for char in key.upper():
        if char not in matrix_str and char in alphabet:
            matrix_str += char
            
    # Para llenar lo que falta
    for char in alphabet:
        if char not in matrix_str:
            matrix_str += char
            
    matrix = [list(matrix_str[i:i+5]) for i in range(0, 25, 5)]
    
    def find_pos(c):
        for row in range(5):
            for col in range(5):
                if matrix[row][col] == c:
                    return row, col
        return -1, -1
        
    # Para quitar guiones y saltos de línea en el textote
    ciphertext = ciphertext.replace("-", "").replace("\n", "").replace(" ", "")
    plaintext = ""
    
    # Descifrado Playfair
    for i in range(0, len(ciphertext), 2):
        c1 = ciphertext[i]
        c2 = ciphertext[i+1]
        r1, col1 = find_pos(c1)
        r2, col2 = find_pos(c2)
        
        if r1 == r2:
            # Misma fila
            plaintext += matrix[r1][(col1 - 1) % 5]
            plaintext += matrix[r2][(col2 - 1) % 5]
        elif col1 == col2:
            # Misma columna
            plaintext += matrix[(r1 - 1) % 5][col1]
            plaintext += matrix[(r2 - 1) % 5][col2]
        else:
            # Diagonales
            plaintext += matrix[r1][col2]
            plaintext += matrix[r2][col1]
            
    return plaintext

def main():
    # Fuerza bruta DES
    print("\n>>> Ataque de fuerza bruta")
    target_base64 = "h+F7XMoHpF0="
    target_bytes = base64.b64decode(target_base64)
    
    found_des_key = None
    playfair_key = None
    
    with open("words.txt", "r") as f:
        words = f.read().splitlines()
        
    for word in words:
        if len(word) != 8:
            continue
            
        key_bytes = int.from_bytes(word.encode("ascii"), "big")
        decrypted_bytes = des_decrypt(target_bytes, key_bytes)
        
        try:
            decrypted_text = decrypted_bytes.decode("ascii")
            if decrypted_text.isalpha(): # Verificar si es la clave para el playfair
                found_des_key = word
                playfair_key = decrypted_text
                print(f"- Llave cifrada en words.txt: {found_des_key}")
                print(f"- Texto descifrado: {playfair_key}\n")
                break
        except UnicodeDecodeError:
            pass

    # Descifrado playfair
    if playfair_key:
        cipher_text = """SHPETXSQZNSPLBMBWFFKCEBRBQMVQSEGOLRBLGXPPSUXHWLGXPDL-
        SZSNAZINELFTEQRGTSRIFWKBRGZVNPWKBQPGPBMZOMGEQMXPHGUF
        DIKBSCMGQMSHVZXTQMFXFOGPSHBWIOSNOQNPWKKCOQMFAVSHSM-
        FOSNDKHGMVSZSHQPIYSQAVPNEGCERZQBQOKSSCOFOHPYQSBKQOZSHP
        FKEGKCRLSNQOIKOQOWPSTDPSBRAVGMVZQZKGFRZVVPZVSHPG-
        VAOHRBGEZVEQHGWMKSNSZSRZPHZVPSZSIRDLSNAZINDLOBFWSKGPZS
        MZQZOWMCAVSHGRMPXGNSPGFPKFHBMGSQSGPEKGQSFSSNOW-
        BLPYSQKBSQBRQSEFSGKSKSUXHWLGXPZSZSNSZKRGFZQPOQDYSXTFRZQ
        MPQRGXECNZPCEGLBQNQPCMESNOWBLPYSCGSOHQPFSRIFWKBQB-
        DTQOQNDOZVMIZPUFDIKBSCNGRYCYBLQGBQOQZAMRZPBRPESNGRQEPE
        SNVPVZBKZVVPPSKSSPQBKGBKQOBKWHKDZVYMMGMQZLKEIOEQGLBR-
        WHUXFOSPZSGPGFQOGKAV"""
        
        final_text = decrypt_playfair(cipher_text, playfair_key)
        print("\n>>> Mensaje descifrado con playfair:\n")
        print(final_text)
    else:
        print("No se encontró la llave en el archivo")

if __name__ == "__main__":
    main()