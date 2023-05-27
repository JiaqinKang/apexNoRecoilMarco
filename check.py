import matplotlib.pyplot as plt

# Recoil pattern offsets
recoil_offsets = [
    [1, -3, 6, 1], [2, -1, 6, 1], [3, -2, 6, 1],
    [4, 0, 5, 3], [5, 0, 5, 3], [6, 0, 2, 3],
    [7, -1, 2, 3], [8, -2, 5, 3], [9, -3, 1, 3],
    [10, -3, 0, 3], [11, -1, 5, 3], [12, 4, 2, 5],
    [13, 3, 0, 3], [14, 3, 1, 3], [15, 4, 1, 3],
    [16, 4, 1, 3], [17, 3, 0, 5], [18, -3, 1, 3],
    [19, -3, 2, 3], [20, -2, 0, 5], [21, -2, 0, 3],
    [22, -3, 0, 3], [23, -3, 0, 3], [24, -2, -2, 1],
    [25, -1, 2, 1], [26, -2, 5, 1], [27, -1, 4, 1],
    [28, 0, 2, 1]
]

# Calculate cumulative positions
cumulative_positions = [[recoil_offsets[-1][1], recoil_offsets[-1][2]]]  # Start with the last position
for i in range(len(recoil_offsets) - 2, -1, -1):
    offset = recoil_offsets[i]
    subsequent_position = cumulative_positions[0]
    new_position = [subsequent_position[0] - offset[1], subsequent_position[1] - offset[2]]
    cumulative_positions.insert(0, new_position)

# Extract coordinates
x = [position[0] for position in cumulative_positions]
y = [position[1] for position in cumulative_positions]

# Pattern indices
pattern_indices = [offset[0] for offset in recoil_offsets]

# Create a 2D scatter plot with color coding
plt.scatter(x, y, c=pattern_indices, cmap='viridis')

# Set labels for each axis
plt.xlabel('X Offset')
plt.ylabel('Y Offset')

# Add a color bar
plt.colorbar(label='Pattern Index')

# Show the plot
plt.show()
